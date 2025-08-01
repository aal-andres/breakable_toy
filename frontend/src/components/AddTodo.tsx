import { useEffect, useState } from "react"
import { addTodo, updateTodo } from "../api/todos"
import { useGlobalState } from "../state/todo-context"

export default function AddTodo(){

     const {todoToUpdate, open,setOpen,setTodos}=useGlobalState()
    
    const [todo, setTodo] = useState<{name:string,priority:string, due_date:string}>({
        name: "",
        priority:"",
        due_date:""

    })

   useEffect(()=>{
         if(todoToUpdate){
        setTodo(() => ({
            name:todoToUpdate.name,
            priority:todoToUpdate.priority,
            due_date:todoToUpdate.dueDate
        }))
    }else{
        setTodo({
            name:"",
            priority:"",
            due_date:""
        })
    }


   },[todoToUpdate,open])

   const handleUpdate = async() => {
                        const response = await updateTodo(todoToUpdate!.id,todo); 
                        setTodos((prevTodos)=>
                            
                            prevTodos.map(item => 
                                item.id == todoToUpdate!.id ? item = response : item
                            )
                        )

        setTimeout(()=>{

            setOpen(false)
        },500)
   }

    if(open){
        return(
            <div className="overlay">
                <div data-testid="modal-container" className="modal">
            <span onClick={()=>setOpen(false)}>X</span>

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
                                <option value="" hidden></option>
                                <option value="HIGH">High</option>
                                <option value="MEDIUM">Medium</option>
                                <option value="LOW">Low</option>
                            </select>
                        </div>


                        <input type="datetime-local" className="calendar" value={todo.due_date} onChange={e => setTodo(todo => ({
                            ...todo,
                            due_date: e.target.value
                        }))}/>
                    </div>

                    {
                        todoToUpdate ?(
                    <button onClick={handleUpdate}>update</button>
                        ): (
                            <button onClick={()=>{addTodo(todo); setOpen(false)}}>Add</button>
                        )
                    }



                </div>
            </div>
        )
    }
}