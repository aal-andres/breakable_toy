import { useEffect, useState } from "react";
import { useGlobalState } from "../state/todo-context";


export default function TodoTable(){

    let rowData
    const {todos, setTodos} = useGlobalState()
    useEffect( ()=>{

           fetch(import.meta.env.VITE_TODO_API+'todo').then(res =>{
            return res.json()
           }).then(data =>{
            console.log(data)
                setTodos(data)
           })


        

        //getTodos()
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
                        <th>[ ]</th>
                        <th>Name</th>
                        <th>priority</th>
                        <th>due date</th>
                        <th>actions</th>
                    </tr>
                </thead>
                <tbody >
                    {rowData}
                </tbody>
            </table>
        </>
    )
}