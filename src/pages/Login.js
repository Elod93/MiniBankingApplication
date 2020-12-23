import React from 'react'
import { Button }  from '../components/Button'
import Form from '../components/LoginForm'
import { Link } from 'react-router-dom';


function Login() {
    return (
        <div className='sign-up-page'>
            <img src='/images/main-page.jpg' alt='main_page_image' />
            <Form />
            <Link to='/home'>
                <Button
                    type='submit'
                className='btns'
                buttonStyle='btn--outline'
                buttonSize='btn--large'>
                SIGN-UP
            </Button> 
            </Link>  
        </div>
    )
}

export default Login
