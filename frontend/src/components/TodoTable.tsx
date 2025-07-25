import { useEffect, useState } from "react";
import { useGlobalState } from "../state/todo-context";


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


    
    
    rowData = todos.map((item:any) =>
        <tr key={item.id}>
                        <th>[ ]</th>
                        <td>{item.name}</td>
                        <td>{item.priority}</td>
                        <td>{item.due_date}</td>
                        <td>{item.actions}</td> 
                    </tr>
    )
    

    return(
        <>
            <table className="todo_table">
                <thead className="table_head">
                    <tr>
                        <th>
                            <span>status</span>
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