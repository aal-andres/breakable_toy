import client from "./httpService"

export const addTodo = async (todo:any) => {
    const response = await client.post('todo', todo)
    console.log(response)
}

export const getFilteredTodos = async(name:string,priority:string,status:string) => {
    const response = await client.get(`todo/search?name=${name}&status=${status}&priority=${priority}`)
    console.log(response)
    return response;
}