import React from 'react';
import { Input } from './Input';
import './LoginForm.css';
import './Input.css';


function Form() {

    return (
        <>
            <form className='sign-up-form'>
                <label className='sign-in-label'>
                    Username:
                </label>
                <Input
                    type="text" 
                    className='input--large'
                    text='Your e-mailadress or username'
                />
                <label  className='sign-in-label'>
                    Password:
                </label>
                <Input
                   type="password" 
                    className='input--large'
                    text='Your password'
                />
            </form>
            
        </>
    )
}

export default Form
