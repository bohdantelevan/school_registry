import React, { useState, useEffect } from 'react';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

const SchoolTable = ({ refreshTrigger }) => {
    const [schools, setSchools] = useState([]);
    const [regionFilter, setRegionFilter] = useState('');
    const [typeFilter, setTypeFilter] = useState('');
    const [isActiveFilter, setIsActiveFilter] = useState('');

    const fetchSchools = () => {
        fetch('/schools')
            .then(response => response.json())
            .then(data => setSchools(data));
    };

    useEffect(() => {
        fetchSchools();
    }, [refreshTrigger]);

    const filteredSchools = schools.filter(school =>
        (!regionFilter || school.region.toLowerCase().includes(regionFilter.toLowerCase())) &&
        (!typeFilter || school.type.toLowerCase().includes(typeFilter.toLowerCase())) &&
        (!isActiveFilter || String(school.active) === isActiveFilter)
    );

    const confirmDeactivate = (id) => {
        confirmAlert({
            title: 'Підтвердіть деактивацію',
            buttons: [
                {
                    label: 'Так',
                    onClick: () => {
                        fetch(`/schools/${id}/deactivate`, {
                            method: 'PATCH',
                            headers: { 'Content-Type': 'application/json' },
                        }).then(() => fetchSchools());
                    }
                },
                {
                    label: 'Ні',
                    onClick: () => {}
                }
            ]
        });
    };

    const confirmActivate = (id) => {
        confirmAlert({
            title: 'Підтвердіть активацію',
            buttons: [
                {
                    label: 'Так',
                    onClick: () => {
                        fetch(`/schools/${id}/activate`, {
                            method: 'PATCH',
                            headers: { 'Content-Type': 'application/json' },
                        }).then(() => fetchSchools());
                    }
                },
                {
                    label: 'Ні',
                    onClick: () => {}
                }
            ]
        });
    };

    return (
        <div>
            <h2>Schools</h2>

            <div>
                <input placeholder="Filter by Region" value={regionFilter} onChange={(e) => setRegionFilter(e.target.value)} />
                <input placeholder="Filter by Type" value={typeFilter} onChange={(e) => setTypeFilter(e.target.value)} />
                <select value={isActiveFilter} onChange={(e) => setIsActiveFilter(e.target.value)}>
                    <option value="">All</option>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                </select>
            </div>

            <table border="1">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>EDRPOU</th>
                    <th>Region</th>
                    <th>Type</th>
                    <th>Active</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {filteredSchools.map(school => (
                    <tr key={school.id}>
                        <td>{school.name}</td>
                        <td>{school.edrpou}</td>
                        <td>{school.region}</td>
                        <td>{school.type}</td>
                        <td>{school.active ? 'Yes' : 'No'}</td>
                        <td>
                            {school.active ? (
                                <button onClick={() => confirmDeactivate(school.id)}>Deactivate</button>
                            ) : (
                                <button onClick={() => confirmActivate(school.id)}>Activate</button>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default SchoolTable;
