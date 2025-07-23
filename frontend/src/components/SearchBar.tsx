import { useState } from "react"
import { getFilteredTodos } from "../api/addTodo"
import { useGlobalState } from "../state/todo-context"

export default function SearchBar(){

  const [parameters, setparameters] = useState<{name:string,priority:string, status:string}>({
          name: "",
          priority:"",
          status:""
  
      })

      const filterTodos = async () => {
        const todoList = await getFilteredTodos(parameters.name,parameters.priority.toUpperCase(),parameters.status.toUpperCase())
        console.log(todoList)
        setTodos(todoList.data)
      }

  const {todos,setTodos} = useGlobalState();

    return (<div className='filter'>
        
        <div className='grouper'>
        <label htmlFor="">Name</label>
        <input type="text" value={parameters.name} name="" id="" onChange={e => setparameters(parameters => ({
                                ...parameters,
                                name: e.target.value
                            }))} />
        </div>
        
        <div className='selectors'>
          
          <div className='left'>

            <div className='grouper'>
              <label htmlFor="">Status</label>
              <select value={parameters.status} name="" id="" onChange={e => setparameters(parameters => ({
                                ...parameters,
                                status: e.target.value
                            }))}>
                <option>Done</option>
                <option>Undone</option>
              </select>
            </div>

            <div className='grouper'>
              <label htmlFor="">Priority</label>
              <select value={parameters.priority} name="" id="" onChange={e => setparameters(parameters => ({
                                ...parameters,
                                priority: e.target.value
                            }))}>
                <option>High</option>
                <option>Medium</option>
                <option>Low</option>
              </select>
            </div>
          </div>

          <div className='right'>
              <button onClick={filterTodos}>Search</button>
          </div>
          
        </div>
      </div>
    )
}