import React, {Component} from 'react';
import './Table.css';
import axios from 'axios';
import moment from 'moment';

class Table extends Component {
   
   constructor() {
      super();
      this.state = {
         id: "",
         date: "",
         iban:"",
         balance:"",
         user: {},
         menu: false

      };
      this.getData = this.getData.bind(this);
      this.toggleMenu = this.toggleMenu.bind(this);
   }
   toggleMenu(){
      this.setState({ menu: !this.state.menu });
 
  }

   componentDidMount() {
      this.getData();
   }
  
   async getData() {     
      let data = await axios
         .get('http://localhost:8080/all_accounts')
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
      const show = (this.state.menu) ? "show" : "" ;
      return (
         <div >
            <h1 id="title">Accounts</h1>
            <hr />
             <button  type="button" onClick={ this.toggleMenu }>
                              <span> Search</span>
                           </button>
                           <div className={"collapse navbar-collapse " + show}>
                              <div >
                                 <label>Name</label>
                                 <input></input>
                                 <label>IBAN</label>
                                 <input></input>
                                 <label>City</label>
                                 <input></input>
                                 <label>Street</label>
                                 <input></input>
                              </div>
                           </div>
            <table className='data' >
                     <thead>
                        <tr>
                           <th>Name</th>
                           <th>Edition time</th>
                           <th>Balance</th> 
                           <th>IBAN</th> 
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
                              <p key={account.id}>{moment(account.date).format("YYYY MM DD/ h:mm")} </p>
                           </td>
                           <td>
                              <p key={account.id}>{account.balance} </p>
                           </td>
                           <td>
                              <p key={account.id}>{account.iban} </p>
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

export default Table