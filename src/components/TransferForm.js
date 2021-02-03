import React, { useState } from 'react';
import './Input.css';
import './TransferForm.css';
import { Button }  from '../components/Button';
import axios from "axios";

const TransferForm = () => {
  const [myIBAN, setIbanFrom] = useState("");
  const [iban, setIbanTo] = useState("");
  const [bill, setBill] = useState("");

  const handleSubmit = async e => {
    e.preventDefault();
    await axios
      .post('http://localhost:8080/transfer/' + sessionStorage.getItem('user'), {myIBAN, iban, bill}).then(res => {
        console.log(res);
        console.log(res.data);
        window.location.href = 'http://localhost:3000/home'
    }).catch(() => {
    alert('IBAN or Bill Incorrect')  
    });
    };
    
 
    return (
        <>
            <form className='sign-up-form'onSubmit={handleSubmit}  >
                <label className='transfer-label'>
                FROM :
                </label>
                <input
                  type='text'
                  value={myIBAN}
                  onChange={({ target }) => setIbanFrom(target.value)}
                  className='input--large' 
                  text='IBAN'
                />
                 <label className='transfer-label'>
                  TO : 
                </label>
                <input
                  type='text'
                  value={iban}
                  onChange={({ target }) => setIbanTo(target.value)}
                  className='input--large'  
                  text='IBAN'
                />
                <label  className='transfer-label'>
                    Bill
                </label>
                <input
                  value={bill}
                  onChange={({ target }) => setBill(target.value)}
                  type='text'
                  className='input--large'
                    
                />
                <Button
                type='submit'
                className='btns'
                buttonStyle='btn--outline'
                buttonSize='btn--large'>
                Transfer
                </Button>       
            </form>
            
        </>
        
    )
}

export default TransferForm
