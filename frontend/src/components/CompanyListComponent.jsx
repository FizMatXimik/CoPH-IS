import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faEdit, faPlus } from '@fortawesome/free-solid-svg-icons'
import BackendService from "../services/BackendService";
import { useNavigate } from 'react-router-dom';
import PaginationComponent from "./PaginationComponent";


const CompanyListComponent = props => {

    const [companies, setCompanies] = useState([]);
    const [hidden, setHidden] = useState(false);
    const [totalCount, setTotalCount] = useState(0);
    const [page, setPage] = useState(0);
    const limit = 20;

    const navigate = useNavigate();

    const onPageChanged = cp => {
        refreshCompanies(cp - 1)
    }

    const refreshCompanies = cp => {
        BackendService.retrieveAllCompanies(cp, limit)
        .then(
            resp => {
                setCompanies(resp.data.content);
                setHidden(false);
                setTotalCount(resp.data.totalElements);
                setPage(cp);
            })
        .catch(()=> { 
            setHidden(true );
            setTotalCount(0);
        })
        .finally()
    }

        if (hidden)
        return null;

    return (
        <div className="m-4">
            <div className="row my-2">
                <h3>Компании</h3>
                <div className="btn-toolbar">
                    <div className="btn-group ms-auto">
                        <button className="btn btn-outline-secondary">
                            <FontAwesomeIcon icon={faPlus} />{' '}Добавить
                        </button>
                    </div>
                    <div className="btn-group ms-2">
                        <button className="btn btn-outline-secondary">
                            <FontAwesomeIcon icon={faTrash} />{' '}Удалить
                        </button>
                    </div>
                </div>
                <div className="row my-2 me-0">
                <PaginationComponent
                        totalRecords={totalCount}
                        pageLimit={limit}
                        pageNeighbours={1}
                        onPageChanged={onPageChanged} />
                    <table className="table table-sm">
                        <thead className="thead-light">
                        <tr>
                            <th>Название</th>
                            <th>ИНН</th>
                            <th>Телефон</th>
                            <th>E-mail</th>
                            <th>
                                <div className="btn-toolbar pb-1">
                                    <div className="btn-group ms-auto">
                                        <input type="checkbox" />
                                    </div>
                                </div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            companies && companies.map((company, index) =>
                                <tr key={company.id}>
                                    <td>{company.name}</td>
                                    <td>{company.inn}</td>
                                    <td>{company.phone}</td>
                                    <td>{company.email}</td>
                                    <td>
                                        <div className="btn-toolbar">
                                            <div className="btn-group ms-auto">
                                                <button className="btn btn-outline-secondary btn-sm btn-toolbar">
                                                    <FontAwesomeIcon icon={faEdit} fixedWidth />
                                                </button>
                                            </div>
                                            <div className="btn-group ms-2 mt-1">
                                                <input type="checkbox" name={index}/>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default CompanyListComponent;