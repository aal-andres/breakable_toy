import { useState } from "react"
import { useGlobalState } from "../state/todo-context";
import { getTodosByPage } from "../api/todos";

export default function PaginationButton(){
     const {hasNextPage,setTodos,setHasNextPage} = useGlobalState()
     const [count, setCount] = useState(1);
    const nextPage = async() => {
        setCount(count => count+=1)
        const future_count = count+1;
        const response = await getTodosByPage(future_count);
        setTodos(response.todos)
        setHasNextPage(response.has_next_page)
        
    }
    const previousPage = async()=>{
        setCount(count => count-=1)
        const future_count = count-1;
        const response = await getTodosByPage(future_count);
        setTodos(response.todos)
        setHasNextPage(response.has_next_page)
    }

    return(
        <div className="pagination-container">
            {
                count>1?(

                    <span data-testid="prev_button" className="arrow_container" onClick={previousPage}>  <span>{"<"}</span>  </span>
                ):(
                    <div className="non_display"></div>
                )
            }
            <button>{count}</button>
            {
                hasNextPage?(

                    <span data-testid="next_button" className="arrow_container" onClick={nextPage}>  <span>{">"}</span>  </span>
                ):(
                    <div className="non_display"></div>
                )
            }
        </div>
            
        
        
    )
}