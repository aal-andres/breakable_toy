import client from "./httpService"

export const addTodo = async (todo:any) => {
    const response = await client.post('todo', todo)
    console.log(response)
}