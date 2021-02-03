import React, { useState } from 'react';
import { Link } from "react-router-dom";
import { Button } from './Button';
import './Navbar.css';

function Navbar() {
    const [click, setClick] = useState(false);
    const [button, setButton] = useState(true);

    const handleClick = () => setClick(!click);
    const closeMobileMenu = () => setClick(false);

    const showButton = () => {
        if (window.innerWidth <= 960) {
            setButton(false);
        } else {
            setButton(true)
        }
    };
function deleteStorage() {
   return sessionStorage.setItem('user',"");  
}
    window.addEventListener('resize', showButton);
    return (
        <>
            <nav className="Navbar">
                <div className="navbar-container">
                    <span to="" className="navbar-logo">
                    E-BANK
                    </span>
                    <div className='menu-icon' onClick={handleClick}>
                        <i className={click ? 'fas fa-times':'fa-fa-bars'} />
                    </div>
                    <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                        <li className='nav-item'>
                            <Link to='/home' className='nav-links' onClick={closeMobileMenu}>
                                Home
                            </Link>
                        </li>
                        <li className='nav-item'>
                            <Link to='/transfer' className='nav-links' onClick={closeMobileMenu}>
                            Transfer
                            </Link>
                        </li>
                        <li className='nav-item'>
                            <Link to='/all_accounts' className='nav-links' onClick={closeMobileMenu}>
                          All Accounts
                            </Link>
                        </li>
                        </ul>
                    <Link to='/' >
                        {button && <Button onClick={() => deleteStorage()} buttonStyle='btn--outline'>Sign-Out</Button>}
                       </Link> 
                </div>
            </nav>         
        </>
    )
}

export default Navbar