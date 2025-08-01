import { useState } from "react"
import { getFilteredTodos } from "../api/todos"
import { useGlobalState } from "../state/todo-context"

export default function SearchBar(){

  const [parameters, setparameters] = useState<{name:string,priority:string, status:string}>({
          name: "",
          priority:"",
          status:""
  
      })

      const filterTodos = async () => {
        const todoList = await getFilteredTodos(parameters.name,parameters.priority.toUpperCase(),parameters.status.toUpperCase())
        setTodos(todoList.todos)
        setHasNextPage(todoList.has_next_page)
      }

  const {setTodos,setHasNextPage} = useGlobalState();

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
                <option value="" hidden></option>
                <option value="DONE">Done</option>
                <option value="UNDONE">Undone</option>
                <option value="ALL">All</option>
              </select>
            </div>

            <div className='grouper'>
              <label htmlFor="">Priority</label>
              <select value={parameters.priority} name="" id="" onChange={e => setparameters(parameters => ({
                                ...parameters,
                                priority: e.target.value
                            }))}>
                <option value="" hidden></option>
                <option value="HIGH">High</option>
                <option value="MEDIUM">Medium</option>
                <option value="LOW">Low</option>
                <option value="ALL">All</option>
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