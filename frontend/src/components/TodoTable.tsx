import { useEffect, useState } from "react";
import { useGlobalState } from "../state/todo-context";


export default function TodoTable(){

    const headers = [
        {
        id:1,
        name:"",
        label: "[ ]"
        },
        {
        id:2,
        name:"name",
        label: "Name"
        },
        {
        id:3,
        name:"priority",
        label:"Priority"
        },
        {
        id:4,
        name:"due_date",
        label:"Due Date"
        },
        {
        id:5,
        name:"actions",
        label: "Actions"
        },
]

    let rowData
    const {todos, setTodos} = useGlobalState()
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
                        {headers.map((header,index)=>
                            <th key={index}>
                                <span>{header.label}</span>
                            </th>
                        )}
                    </tr>
                </thead>
                <tbody >
                    {rowData}
                </tbody>
            </table>
        </>
    )
}