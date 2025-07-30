import { createContext, useContext, useState, type Dispatch, type SetStateAction } from "react";
import type { TimeStatistics, Todo } from "../types/todo";



interface GlobalStateTypeContext{
    todos:Todo[]
    setTodos:Dispatch<SetStateAction<Todo[]>>
    open: boolean;
    setOpen: Dispatch<SetStateAction<boolean>>;
    todoToUpdate:Todo|null;
    setUpdateTodo: Dispatch<SetStateAction<any>>;
    timeStatistics:TimeStatistics | null;
    setTimeStatistics:Dispatch<SetStateAction<TimeStatistics|any>>;
    hasNextPage:boolean;
    setHasNextPage:Dispatch<SetStateAction<boolean>>;
}

 const GlobalState = createContext<GlobalStateTypeContext>({
    todos:[],
    setTodos: () =>{},
    open:false,
    setOpen: () => {},
    todoToUpdate:null,
    setUpdateTodo:()=>{},
    timeStatistics:null,
    setTimeStatistics:()=>{},
    hasNextPage:true,
    setHasNextPage:()=>{}

 });

export const GlobalStateProvider = ({children}:{children:React.ReactNode}) => {

    const [todos, setTodos] = useState<any[]>([]);
    const [open, setOpen] = useState<boolean>(false);
    const [todoToUpdate, setUpdateTodo] = useState(null);
    const [timeStatistics,setTimeStatistics] = useState({
        total_avg:'',
        low_avg:'',
        high_avg:'',
        medium_avg:''
    });
    const [hasNextPage, setHasNextPage] = useState(false);

    return (
        
        <GlobalState.Provider value={{todos, setTodos,open,setOpen,todoToUpdate,setUpdateTodo,timeStatistics,setTimeStatistics,hasNextPage,setHasNextPage}}>
            {children}
        </GlobalState.Provider>

       
    )

}
export const useGlobalState = () => {
    const context = useContext(GlobalState);

    if(!context){
        console.log('aaaaaaaa')
    }
    console.log('contextooooooo',context)
    return context;
}