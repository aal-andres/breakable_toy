import { getTodosAndTImeMterics } from "../api/todos"
import { useGlobalState } from "../state/todo-context";
const { setTodos} = useGlobalState()


export const resetTable = async() => {
    const response = await getTodosAndTImeMterics();
    setTodos(response.todos)
}