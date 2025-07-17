export default function AddTodo({active}:{active:boolean}){
    

    const setActive = () =>{
        active = false
        console.log(active)
    }
    if(active){
        return(
            <div className="overlay">
                <div className="modal" onClick={setActive}></div>
            </div>
        )
    }
}