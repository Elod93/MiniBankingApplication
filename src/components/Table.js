import React, {Component} from 'react';
import './Table.css';
import axios from 'axios';
import moment from 'moment';
import { Link } from "react-router-dom";


class Table extends Component {
   
   constructor() {
      super();
      this.state = {
         id: "",
         date: "",
         iban:"",
         balance:"",
         user: {},
         flag: false 
      };
      this.getData = this.getData.bind(this);
      this.saveIdFlag = this.saveIdFlag.bind(this);
      this.saveAccountId = this.saveAccountId.bind(this);
   }
   saveIdFlag(){
      this.setState({ flag: !this.state.flag });
   }
   saveAccountId(id) {
      sessionStorage.setItem('account_id', id);    
   }
   componentDidMount() {
      this.getData();
   }
  
   async getData() {     
      let data = await axios
         .get('http://localhost:8080/home/' + sessionStorage.getItem('user'))
         .then(function (response) {
            return response;
         })
         .catch(function (error) {
            console.log(error);
         });
      this.setState({ accounts: data.data });
      console.log(data.data)
   }
   
 
   render() {
      const { accounts } = this.state;
      
      return (
         <div >
            <h1 id="title">Accounts</h1>
            <hr />
            <table className='data' >
                     <thead>
                        <tr>
                           <th>Name</th>
                           <th>Edition time</th>
                           <th>Balance</th> 
                           <th>IBAN</th> 
                           <th>History</th> 
                        </tr>
                     </thead>
            {accounts &&
               accounts.map(account => {
                  return (
                  <tbody> 
                        <tr>
                           <td>
                              <p key={account.id}>{account.user.fullName} </p>
                           </td>
                           <td>
                              <p key={account.date}>{moment(account.date).format("YYYY MM DD/ h:mm")} </p>
                           </td>
                           <td>
                              <p key={account.balance}>{account.balance} </p>
                           </td>
                           <td>
                              <p key={account.iban}>{account.iban} </p>
                           </td> 
                           <td>
                              <Link to='/histories'>
                              <button type="button"
                                 onClick={() =>
                                 {
                                    this.saveIdFlag()
                                    this.saveAccountId( account.id )
                                 }}
                              >     
                              <span> History </span>
                                 </button>
                              </Link>   
                           </td> 
                           </tr>
                     </tbody>        
                  
                  );
               })}
               </table>
         </div>
      );
   }

}
console.log("id: "+sessionStorage.getItem('account_id'));
export default Table