import React from 'react';
import Navbar from '../components/Navbar';
import TransferForm from '../components/TransferForm';


function Transfer() {
    return (
        <div >
            <Navbar />
            <img src='/images/img-1.jpg' alt='' className='account-img' />
            <div className='sign-up-page' >
                <TransferForm />
            </div>        
        </div>
    )
}

export default Transfer
