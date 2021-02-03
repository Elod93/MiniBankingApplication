import React from 'react';
import Navbar from '../components/Navbar';
import Table from '../components/HistoryTable';



function Histories() {
    return (
        <div >
        <Navbar/>
            <img src='/images/img-1.jpg' alt='' className='account-img' />
            < Table  className='table' />
        </div>
    )
}

export default Histories;