export default function AddTodo({active, setActive}:{active:boolean, setActive:()=> void}){
    

    
    if(active){
        return(
            <div className="overlay">
                <div className="modal">
            <span onClick={setActive}>X</span>

                    <div className="grouper">
                        <label htmlFor="">name</label>
                        <input type="text" />
                    </div>

                    <div className="calendar_priority_container">
                        <div className="grouper">
                            <label htmlFor="">priority</label>
                            <select name="" id=""></select>
                        </div>


                        <input type="date"  className="calendar"/>
                    </div>


                    <button>Add</button>



                </div>
            </div>
        )
    }
}