import { useEffect, useState } from "react";
import { useGlobalState } from "../state/todo-context";
import { checkTodo, deleteTodo, unCheckTodo } from "../api/todos";
import type { todo } from "../types/todo";


export default function TodoTable(){
    const [showUndoneFirst,setShowUndoneFirst] = useState(false);
    const [showNewestFirst,setShowNewestFirst] = useState(false);
    const {todos, setTodos} = useGlobalState()
    const sortByPriority = () => {
        const sortedTodo = [...todos].sort((a,b)=>{
            const order = showUndoneFirst ? ["UNDONE","DONE"] : ["DONE","UNDONE"]
            return order.indexOf(a.status) - order.indexOf(b.status);
        })
        setTodos(sortedTodo)
        setShowUndoneFirst(!showUndoneFirst)
        console.log('el estadooo',showUndoneFirst)
    }

    const sortByDate = () => {
        const sortedTodos = [...todos].sort((a,b)=>{
            return showNewestFirst ? Date.parse(b.due_date) - Date.parse(a.due_date) : Date.parse(a.due_date) - Date.parse(b.due_date)
        })
        setTodos(sortedTodos)
        setShowNewestFirst(!showNewestFirst)
    }


    let rowData
    useEffect( ()=>{

           fetch(import.meta.env.VITE_TODO_API+'todos').then(res =>{
            return res.json()
           }).then(data =>{
            console.log(data)
                setTodos(data)
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
        let deletedTodo:todo = await deleteTodo(todo.id);
        console.log(deletedTodo)
        setTodos((prevTodo:any[])=>{
            return prevTodo.filter(item =>item.id != todo.id)
        })

    }
    
    rowData = todos.map((item:any) =>
        <tr key={item.id}>
                        <th> <input className="status_checkbox" type="checkbox" checked={showCheckedTodo(item)}  onClick={()=>toggleTodoState(item)} /> </th>
                        <td>{item.name}</td>
                        <td>{item.priority}</td>
                        <td>{item.due_date}</td>
                        <td onClick={()=> {console.log(item)}}><span>edit </span> <span>/</span>  <span onClick={()=>handledeleteTodo(item)}>delete</span></td> 
                    </tr>
    )
    

    return(
        <>
            <table className="todo_table">
                <thead className="table_head">
                    <tr>
                        <th>
                            <span>  <input className="status_checkbox" type="checkbox" />  </span>
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