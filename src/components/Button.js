import React from 'react';
import './Button.css';

const STYLES = ['btn--prymary', 'btn--outline'];
const SIZE = ['btn--medium', 'btn--large'];

export const Button = ({
    children,
    type,
    buttonStyle,
    buttonSize
}) => {
    const checkButtonStyle = STYLES.includes(buttonStyle) ? buttonStyle : STYLES[0];
    const checkButtonSize = SIZE.includes(buttonSize) ? buttonSize : SIZE[0];

    return (
        
            <button
                className={`${checkButtonStyle} ${checkButtonSize}`}
                type={type}
                
            >
                {children}
                
            
            </button>    
    );
};