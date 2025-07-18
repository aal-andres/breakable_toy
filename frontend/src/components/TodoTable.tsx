import { useEffect, useState } from "react";
export default function TodoTable(){


    let rowData
    const [todos, setTodos] = useState([]);
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
                        <th>id</th>
                        <td>{item.name}</td>
                        <td>{item.priority}</td>
                        <td>{item.due_date}</td>
                        <td>{item.actions}</td> 
                    </tr>
    )

    return(
        <>
            <table>
                <thead>
                    <tr>
                        <th>[ ]</th>
                        <th>Name</th>
                        <th>priority</th>
                        <th>due date</th>
                        <th>actions</th>
                    </tr>
                </thead>
                <tbody>
                    {rowData}
                </tbody>
            </table>
        </>
    )
}