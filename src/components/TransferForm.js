import React from 'react'
import { Input } from './Input'
import './TransferForm.css'

function TransferForm() {
    return (
        <>
            <form className='sign-up-form'>
                <label className='transfer-label'>
                FROM :
                </label>
                <Input
                    type='text'
                    className='input--large' 
                    text='IBAN'
                />
                 <label className='transfer-label'>
                  TO : 
                </label>
                <Input
                    type='text'
                    className='input--large'  
                    text='IBAN'
                />
                <label  className='transfer-label'>
                    Bill
                </label>
                <Input
                    type='password'
                    className='input--large'
                    
                />
            </form>
            
        </>
    )
}

export default TransferForm
