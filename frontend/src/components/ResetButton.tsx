import { getTodosAndTImeMterics } from "../api/todos";
import { useGlobalState } from "../state/todo-context";

export default function ResetButton(){
    const { setTodos} = useGlobalState()
    const resetTable = async() => {
        window.location.reload()
        
    }
    return(
        <button onClick={resetTable}>
            <span className="material-symbols-outlined">
            refresh
            </span>
        </button>
    )
}