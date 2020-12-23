import React, { Component } from 'react';
import './Table.css'

class Table extends Component {
   constructor(props) {
      super(props) 
      this.state = { 
         data: [
            { name: 'Kristaly Elod', IBAN: 'CH2532532534544224', bill: 1000000 },
            { name: 'Kristaly Elod', IBAN: 'CH2532532534544224', bill: 1000000 }
         ]
      }
   }
   renderTableData() {
      return this.state.data.map((data, index) => {
         const {  name, IBAN, bill } = data 
         return (
            <tr key={IBAN}>
               <td>{name}</td>
               <td>{IBAN}</td>
               <td>{bill}</td>
               
            </tr>
         )
      })
   }
   renderTableHeader() {
      let header = Object.keys(this.state.data[0])
      return header.map((key, index) => {
         return <th key={index}>{key.toUpperCase()}</th>
      })
   }


   render() {
      return (
         <div>
            <h1 id='title'>Account</h1>
            <table id='data'>
               <tbody>
                  <tr>{this.renderTableHeader()}</tr>
                  {this.renderTableData()}
               </tbody>
            </table>
         </div>
      )
   }


}

export default Table