import { useState } from "react"
import { addTodo } from "../api/todos"

export default function AddTodo({active, setActive}:{active:boolean, setActive:()=> void}){
    
    const [todo, setTodo] = useState<{name:string,priority:string, due_date:string}>({
        name: "",
        priority:"",
        due_date:""

    })

    if(active){
        return(
            <div className="overlay">
                <div className="modal">
            <span onClick={setActive}>X</span>

                    <div className="grouper">
                        <label htmlFor="">name</label>
                        <input type="text" value={todo.name} onChange={e => setTodo(todo => ({
                            ...todo,
                            name: e.target.value
                        }))}/>
                    </div>

                    <div className="calendar_priority_container">
                        <div className="grouper">
                            <label htmlFor="">priority</label>
                            <select value={todo.priority} name="" id="" onChange={e => setTodo(todo => ({
                                ...todo,
                                priority: e.target.value.toUpperCase()
                            }))}>
                                <option>High</option>
                                <option>Medium</option>
                                <option>Low</option>
                            </select>
                        </div>


                        <input type="datetime-local" className="calendar" value={todo.due_date} onChange={e => setTodo(todo => ({
                            ...todo,
                            due_date: e.target.value
                        }))}/>
                    </div>


                    <button onClick={()=>{addTodo(todo)}}>Add</button>



                </div>
            </div>
        )
    }
}