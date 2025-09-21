import React from 'react'
import Sidebar from '../Components/Doctor/Sidebar/Sidebar.tsx'
import Header from '../Components/Header/Header.tsx'
import { Outlet } from 'react-router-dom'


const DoctorDashboard = () => {
  return (
   <div className='flex'>
           <Sidebar/>
           <div className='w-full flex flex-col'>
            <Header/>
               <Outlet />
               
               
           </div>
       </div>
  )
}

export default DoctorDashboard