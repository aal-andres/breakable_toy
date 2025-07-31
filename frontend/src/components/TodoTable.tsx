import { useEffect, useState } from "react";
import { useGlobalState } from "../state/todo-context";
import { checkTodo, deleteTodo, getTodosAndTImeMterics, unCheckTodo } from "../api/todos";
import type { Todo } from "../types/todo";


export default function TodoTable(){
    const [showUndoneFirst,setShowUndoneFirst] = useState(false);
    const [showNewestFirst,setShowNewestFirst] = useState(false);
    const {todos, setTodos,setOpen,setUpdateTodo,setTimeStatistics,setHasNextPage} = useGlobalState()
    const [showAsCheck, setShow] = useState(false);
    const sortByPriority = () => {
        const sortedTodo:Todo[] = [...todos].sort((a,b)=>{
            const order = showUndoneFirst ? ["UNDONE","DONE"] : ["DONE","UNDONE"]
            return order.indexOf(a.status) - order.indexOf(b.status);
        })
        setTodos(sortedTodo)
        setShowUndoneFirst(!showUndoneFirst)
        console.log('el estadooo',showUndoneFirst)
    }

    const sortByDate = () => {
        const sortedTodos = [...todos].sort((a,b)=>{
            return showNewestFirst ? Date.parse(b.dueDate) - Date.parse(a.dueDate) : Date.parse(a.dueDate) - Date.parse(b.dueDate)
        })
        setTodos(sortedTodos)
        setShowNewestFirst(!showNewestFirst)
    }


    let rowData
    useEffect( ()=>{

          // fetch(import.meta.env.VITE_TODO_API+'todos').then(res =>{
          //  return res.json()
          // }).then(data =>{
          //  console.log(data.todos)
          //      setTodos(data.todos)
          // })
        const response  =  getTodosAndTImeMterics();
        console.log(response);
        response.then(data => {
            setTodos(data.todos)
            console.log('tetas',data)
           setTimeStatistics(data.timeMetrics)
           setHasNextPage(data.has_next_page)
        })

        

        
    },[])


    const showCheckedTodo= (todo:any):boolean => {
        const checked = todo.status==="DONE"?true:false
        return checked;
    }

    const toggleTodoState = async(todo:any)=>{

        let updatedTodo:any; 
        if(todo.status === 'UNDONE'){

            updatedTodo= await checkTodo(todo.id)
        }else{
            updatedTodo = await unCheckTodo(todo.id)
        }

        setTodos((prevTodos: any[]) =>
            prevTodos.map(item =>
                item.id === todo.id ? { ...item, status: updatedTodo.status } : item
            )
        );
        console.log(updatedTodo)
    }

    const handledeleteTodo = async(todo:any) => {
        let deletedTodo:boolean = await deleteTodo(todo.id);
        console.log(deletedTodo)
        setTodos((prevTodo:any[])=>{
            return prevTodo.filter(item =>item.id != todo.id)
        })

    }

    


    
    rowData = todos.map((item:Todo) =>
        <tr key={item.id}>
                        <th> <input className="status_checkbox" type="checkbox" checked={showCheckedTodo(item)}  onClick={()=>toggleTodoState(item)} /> </th>
                        <td>{item.name}</td>
                        <td>{item.priority}</td>
                        <td>{item.dueDate}</td>
                        <td onClick={()=> {console.log(item)}}><span onClick={()=>{setOpen(true)

                            setUpdateTodo(item)
                        }}>edit </span> <span>/</span>  <span onClick={()=>handledeleteTodo(item)}>delete</span></td> 
                    </tr>
    )
    

    return(
        <>
            <table className="todo_table">
                <thead className="table_head">
                    <tr>
                        <th>
                            <span>  <input className="status_checkbox" checked={showAsCheck} type="checkbox" onClick={()=> setShow(showAsCheck=>!showAsCheck)}/>  </span>
                        </th>
                        <th>
                            <span>name</span>
                        </th>
                        <th onClick={sortByPriority}>
                            <span>priority</span> <span>{"<>"}</span>
                        </th>
                        <th onClick={sortByDate}>
                            <span>Due Date</span>
                        </th><th>
                            <span>Actions</span>
                        </th>
                    </tr>
                </thead>
                <tbody >
                    {rowData}
                </tbody>
            </table>
        </>
    )
}