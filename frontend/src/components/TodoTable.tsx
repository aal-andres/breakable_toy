import { useEffect, useState } from "react";
import { useGlobalState } from "../state/todo-context";
import { checkTodo, deleteTodo, getTodosAndTImeMterics, unCheckTodo } from "../api/todos";
import type { Todo } from "../types/todo";


export default function TodoTable(){
    const [showUndoneFirst,setShowUndoneFirst] = useState(false);
    const [showHighFirst,setShowHighFirst] = useState(false);
    const [showNewestFirst,setShowNewestFirst] = useState(false);
    const {todos, setTodos,setOpen,setUpdateTodo,setTimeStatistics,setHasNextPage} = useGlobalState()
    const [showAsCheck, setShow] = useState(false);
    const sortByPriority = () => {
        console.log('mi rey que pasooooo')
        setShowUndoneFirst(!showUndoneFirst)
        const sortedTodo:Todo[] = [...todos].sort((a,b)=>{
            const order = showUndoneFirst ? ["UNDONE","DONE"] : ["DONE","UNDONE"]
            console.log('a status',order)
            return order.indexOf(a.status) - order.indexOf(b.status);
        })
        setTodos(sortedTodo)
        console.log('el estadooo',showUndoneFirst)
    }

    const sortTodosByPriority = () => {
        setShowHighFirst(!showHighFirst)
        const sortedTodo:Todo[] = [...todos].sort((a,b)=>{
            const order = showHighFirst ? ["HIGH","MEDIUM","LOW"] : ["LOW","MEDIUM","HIGH"]
            console.log('el orden',order)
            return order.indexOf(a.priority) - order.indexOf(b.priority);
        })
        setTodos(sortedTodo)
    }

    const sortByDate = () => {
        const sortedTodos = [...todos].sort((a,b)=>{
            return showNewestFirst ? Date.parse(b.dueDate) - Date.parse(a.dueDate) : Date.parse(a.dueDate) - Date.parse(b.dueDate)
        })
        setTodos(sortedTodos)
        setShowNewestFirst(!showNewestFirst)
    }

    const setRowBackgroundColor = (todo:Todo) => {
        const millisecondsInAWeek = 1000 * 60 * 60 * 24 * 7
        
        if(todo.status == 'DONE'){
            return 'checked-task'
        }

        if(todo.dueDate==null){
            return 'no-background'
        }
        
        const timeRemainingMilli = Date.parse(todo.dueDate) - Date.parse(todo.createdAt);
        const weeksRemaining = timeRemainingMilli / millisecondsInAWeek;

        if(weeksRemaining<=1){
            return 'red'
        }
        console.log('parse', weeksRemaining)
        return 'green'
    }

    const checkIfTaskHasExpired = (todo:Todo):boolean => {
        if(todo.dueDate!=null){
            if(Date.now() > Date.parse(todo.dueDate)){
                return true
            }
        }
        return false
    }
    let rowData
    useEffect( ()=>{

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
                item.id === todo.id ? { ...item, status: updatedTodo.todo.status } : item
            )
        );
        console.log('shit',updatedTodo)
        setTimeStatistics(updatedTodo.timeStatistics)
    }

    const handledeleteTodo = async(todo:any) => {
        let deletedTodo:boolean = await deleteTodo(todo.id);
        console.log(deletedTodo)
        setTodos((prevTodo:any[])=>{
            return prevTodo.filter(item =>item.id != todo.id)
        })

    }

    


    
    
    rowData = todos.map((item:Todo) =>
        <tr key={item.id} className={`row-color ${setRowBackgroundColor(item)}`}>
                        <th> <input className="status_checkbox" type="checkbox" checked={showCheckedTodo(item)}  onClick={()=>toggleTodoState(item)} /> </th>
                        <td>{item.name}</td>
                        <td>{item.priority}</td>
                        {
                            checkIfTaskHasExpired(item) ?(
                                <td>Expired</td>
                            ):(

                                <td>{item.dueDate}</td>
                            )
                        }
                        <td onClick={()=> {console.log(item)}} className="actions-container">
                            <span onClick={()=>{setOpen(true)
                            setUpdateTodo(item)
                        }} className="material-symbols-outlined">edit </span>   
                        <span className="material-symbols-outlined" onClick={()=>handledeleteTodo(item)}>delete</span>
                        </td> 
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
                        <th onClick={sortTodosByPriority} className="sorting-action-container">
                            <span>priority</span> 
                            {
                                showHighFirst? (

                                    <span className="material-symbols-outlined">arrow_downward</span>
                                ):(
                                    <span className="material-symbols-outlined">arrow_upward</span>
                                )
                            }
                        </th>
                        <th onClick={sortByDate} className="sorting-action-container">
                            <span>Due Date</span>
                            {
                                showNewestFirst? (

                                    <span className="material-symbols-outlined">arrow_downward</span>
                                ):(
                                    <span className="material-symbols-outlined">arrow_upward</span>
                                )
                            }
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