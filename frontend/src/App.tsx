import { useState } from 'react'
import './App.css'
import SearchBar from './components/SearchBar'
import TodoTable from './components/TodoTable'
import TimeMetrics from './components/TimeTracker'
import AddTodo from './components/AddTodo'

function App() {
  
  const [isActive, setActive] = useState(true);
  
  const closeModal = ()=> {
    setActive(isActive => !isActive)
  }

  return (
    <>
      <AddTodo active={isActive} setActive={closeModal}/>
      <div className='table'>

      <SearchBar/>


      <button className='add_button' onClick={closeModal}>Add Todo +</button>

      <TodoTable/>

      <TimeMetrics/>


      </div>
    </>
  )
}

export default App
