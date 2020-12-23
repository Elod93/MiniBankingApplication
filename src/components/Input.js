import React from 'react';
import './Input.css';

const SIZE=['input--large' ,'input--medium']

export const Input = ({
    type ,
    text,
    inputSize
})=> {
    const checkInputSize=SIZE.includes(inputSize) ? inputSize : SIZE[0];

    return (
        <input
            type={type}
            className={`input ${checkInputSize}`}
            placeholder = { text }
        />   
    );
};


