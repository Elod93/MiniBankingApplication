import React,{useState} from 'react';
import './LoginForm.css';
import './Input.css';
import { Button }  from '../components/Button';
import axios from "axios";

const Form = () => {
const [username, setUsername] = useState("");
const [password, setPassword] = useState("");
const params = new URLSearchParams();
const handleSubmit = async e => {
    e.preventDefault();
    const user = { username, password };
    params.append('username', username);
    params.append('password', password);
    axios.post('http://localhost:8080/login', params).then(res => {
        console.log(res);
        console.log(res.data);
        sessionStorage.setItem('user', username);
        console.log(user);
        window.location.href = 'http://localhost:3000/home'
    }).catch(() => {
    alert('Username or Password Incorrect')  
    });
    };
    return (
        <>
            <form className='sign-up-form' onSubmit={handleSubmit} >
                <label className='sign-in-label'>
                    Username:
                </label>
                <input
                    type="text"
                    value={username}
                    onChange={({ target }) => setUsername(target.value)}
                    className='input--large'
                    text='Your e-mailadress or username'
                
                />
                
                <label  className='sign-in-label'>
                    Password:
                </label>
                <input
                    type="password" 
                    value={password}
                    onChange={({ target }) => setPassword(target.value)}
                    className='input--large'
                    text='Your password'
                />      
                <Button
                type='submit'
                className='btns'
                buttonStyle='btn--outline'
                buttonSize='btn--large'>
                SIGN-UP
            </Button>       
            </form>   
        </>
    )
    }

console.log(sessionStorage.getItem('user'))
export default Form

