import React, {Component} from 'react';
import './Table.css';
import axios from 'axios';
import moment from 'moment';

class Table extends Component {
    constructor() {
        super();
        this.state = {
            id: "",
            crediting: "",
            transferred_amount: "",
            amount:"",
            date: "",

        };
        this.getData = this.getData.bind(this);
    }

    componentDidMount() {
        this.getData();
    }
    async getData() {
        let data = await axios
            .get('http://localhost:8080/history/' + sessionStorage.getItem('account_id'))
            .then(function (response) {
                return response;
            })
            .catch(function (error) {
                console.log(error);
            });
        this.setState({ histories: data.data });
        console.log(data.data)
    }
    render() {
        const { histories } = this.state;
        return (
            <div >
                <h1 >Account history</h1>
                <hr />
                <table className='data' >
                    <thead>
                        <tr>
                            <th>Crediting</th>
                            <th>Transfered amount</th>
                            <th>Balance</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    {histories &&
                        histories.map(history => {
                            return (
                                <tbody>
                                    <tr>
                                        <td>
                                            <p key={history.id}>{(history.crediting) ? history.crediting :"0"} </p>
                                        </td>
                                        <td>
                                            <p key={history.id}>{(history.transferredAmount) ? history.transferredAmount : "0"} </p>
                                        </td>
                                        <td>
                                            <p key={history.id}>{history.amount} </p>
                                        </td>
                                        <td>
                                            <p key={history.id}>{moment(history.date).format("YYYY MM DD/ h:mm")} </p>
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