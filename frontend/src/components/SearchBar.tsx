
export default function SearchBar(){
    return (<div className='filter'>
        
        <div className='grouper'>
        <label htmlFor="">Name</label>
        <input type="text" />
        </div>
        
        <div className='selectors'>
          
          <div className='left'>

            <div className='grouper'>
              <label htmlFor="">State</label>
              <select></select>
            </div>

            <div className='grouper'>
              <label htmlFor="">Priority</label>
              <select></select>
            </div>
          </div>

          <div className='right'>
              <button>Search</button>
          </div>
          
        </div>
      </div>
    )
}