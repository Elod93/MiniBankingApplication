import React from 'react';
import { Link } from 'react-router-dom';
import { Button } from '../components/Button';
import Navbar from '../components/Navbar';
import TransferForm from '../components/TransferForm';


function Transfer() {
    return (
        <div >
            <Navbar />
            <img src='/images/img-1.jpg' alt='' className='account-img' />
            <div className='sign-up-page' >
                <TransferForm />
            <Link to='/home'>
                <Button
                className='btns'
                buttonStyle='btn--outline'
                buttonSize='btn--large'>
                Transfer
            </Button> 
            </Link>  
            </div>
            


            
        </div>
    )
}

export default Transfer
