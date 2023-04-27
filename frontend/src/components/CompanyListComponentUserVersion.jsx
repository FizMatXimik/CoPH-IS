import React, { useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Alert from './Alert'
import { faTrash, faPhone, faEnvelope, faEdit, faPlus } from '@fortawesome/free-solid-svg-icons'
import {Form} from "react-bootstrap";
import BackendService from "../services/BackendService";
import { useNavigate } from 'react-router-dom';
import PaginationComponent from "./PaginationComponent";


const CompanyListComponent = props => {

    const [companies, setCompanies] = useState([]);
    const [search, setSearch] = useState("");
    const [isEmpty, setIsEmpty] = useState(false);
    const [hidden, setHidden] = useState(false);
    const [totalCount, setTotalCount] = useState(0);
    const [page, setPage] = useState(0);
    const limit = 20;
    const limitSearch = 1000;

    const navigate = useNavigate();

    const onPageChanged = cp => {
        refreshCompanies(cp - 1)
    }

    const refreshCompanies = cp => {
        BackendService.retrieveAllCompanies(search, cp, limit)
        .then(
            resp => {
                setCompanies(resp.data.content);
                setHidden(false);
                setTotalCount(resp.data.totalElements);
                setPage(cp);
                setIsEmpty(resp.data.empty);
            })
        .catch(()=> { 
            setHidden(true );
            setTotalCount(0);
        })
        .finally()
    }

    const onSubmit = (event) => {
        event.preventDefault();
        event.stopPropagation();
        if (!search) {
            refreshCompanies(0);
        } else {
            BackendService.retrieveAllCompanies(search, 0, limit)
                .then(
                    resp => {
                        setCompanies(resp.data.content);
                        setHidden(false);
                        setTotalCount(resp.data.totalElements);
                        setPage(0);
                        setIsEmpty(resp.data.empty);
                    })
                .catch(() => {
                    setHidden(true );
                    setTotalCount(0);
                })
        }

    }

    const updateCompanyClicked = id => {
        navigate(`/companies/${id}`)
    }

    const createApplication = name => {
        navigate(`/applications/create/${name}`)
    }

    const addCompanyClicked = () => {
        navigate(`/companies/-1`)
    }

    if (hidden)
    return null;

    if (isEmpty) {
        return (
            <div className="m-4">
                <div className="row my-2">
                    <h3>Компании</h3>
                    <div className="row my-2 me-0">
                        <div className="col">
                            <Form className="row mb-5" onSubmit={onSubmit}>
                                <Form.Group className="col">
                                    <Form.Control
                                        type="search"
                                        placeholder="Поиск компаний"
                                        onChange={(e) => {setSearch(e.target.value)}}
                                        value={search}
                                        autoComplete="off"
                                    />
                                </Form.Group>
                                <button className="col-auto btn btn-search" type="submit">
                                    Найти
                                </button>
                            </Form>
                            <div className="row mb-5">
                                <h1>Упс... Компаний не найдено</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    return (
        <div className="m-4">
            <div className="row my-2">
                <h3 className={"col"}>Компании</h3>
                <div className="btn-toolbar col me-2">
                    <div className="btn-group ms-auto">
                        <button className="btn btn-outline-primary"
                                onClick={addCompanyClicked}>
                            <FontAwesomeIcon icon={faPlus} />{' '}Добавить
                        </button>
                    </div>
                </div>
                <div className="row my-2 me-0">
                    <div className="col">
                        <Form className="row mb-5" onSubmit={onSubmit}>
                            <Form.Group className="col">
                                <Form.Control
                                    type="search"
                                    placeholder="Поиск компаний"
                                    onChange={(e) => {setSearch(e.target.value)}}
                                    value={search}
                                    autoComplete="off"
                                />
                            </Form.Group>
                            <button className="col-1 btn btn-search" type="submit">
                                Найти
                            </button>
                        </Form>
                    </div>
                    <PaginationComponent
                                totalRecords={totalCount}
                                pageLimit={limit}
                                pageNeighbours={1}
                                onPageChanged={onPageChanged} />
                    <table className="table-sm ">
                        <tbody>
                        {
                            companies && companies.map((company, index) =>
                                <tr className="row company m-2 my-3"  key={company.id}>
                                    <td className="col p-3 py-4">
                                        <div className="row ps-3">
                                        {company.name}
                                        </div>
                                        <div className="row my-2">
                                            <div className="col-auto phone">
                                            <FontAwesomeIcon className="px-1" icon={faPhone} />{company.phone}
                                            </div>
                                            <div className="col-auto email">
                                            <FontAwesomeIcon className="px-2" icon={faEnvelope} />{company.email}
                                            </div>
                                        </div>
                                    </td>
                                    <td className="col-auto p-3 create-z">
                                        <div className="btn-toolbar">
                                            <div className="col-auto btn-group ms-auto">
                                                { !props.isAdmin && !props.isCompany && <button className="btn btn-outline-dark btn-sm btn-toolbar" onClick={() =>
                                                    createApplication(company.name)}>
                                                    <span className="p-0">Создать заявку</span>
                                                    <FontAwesomeIcon className="p-1" icon={faEdit} fixedWidth />
                                                </button>}
                                                { props.isAdmin && <button className="btn btn-warning btn-sm btn-toolbar" onClick={() =>
                                                    updateCompanyClicked(company.id)}>
                                                    <FontAwesomeIcon className="p-1" icon={faEdit} fixedWidth />
                                                </button>}
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