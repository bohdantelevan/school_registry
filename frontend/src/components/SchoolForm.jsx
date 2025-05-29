import React, { useState } from 'react';

function SchoolForm({ onAdd }) {
    const [newSchool, setNewSchool] = useState({
        name: '',
        edrpou: '',
        region: '',
        type: 'ГІМНАЗІЯ',
        active: 'true'
    });

    const handleAddSchool = async () => {
        try {
            const response = await fetch('/schools', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newSchool)
            });
            if (!response.ok) {
                throw new Error('Failed to add school');
            }
            setNewSchool({ name: '', edrpou: '', region: '', type: 'ГІМНАЗІЯ', active: '' });
            onAdd();
        } catch (error) {
            console.error('Error adding school:', error);
        }
    };

    return (
        <div>
            <h2>Add New School</h2>
            <input
                type="text"
                placeholder="Name"
                value={newSchool.name}
                onChange={(e) => setNewSchool({...newSchool, name: e.target.value})}
            />
            <input
                type="number"
                placeholder="EDRPOU"
                value={newSchool.edrpou}
                onChange={(e) => setNewSchool({...newSchool, edrpou: e.target.value})}
            />
            <input
                type="text"
                placeholder="Region"
                value={newSchool.region}
                onChange={(e) => setNewSchool({...newSchool, region: e.target.value})}
            />
            <select
                value={newSchool.type}
                id='type'
                onChange={(e) => setNewSchool({...newSchool, type: e.target.value})}>
                <option value="ГІМНАЗІЯ">ГІМНАЗІЯ</option>
                <option value="ЛІЦЕЙ">ЛІЦЕЙ</option>
                <option value="ЗЗСО">ЗЗСО</option>
            </select>
            <select
                value={newSchool.active}
                id='active'
                onChange={(e) => setNewSchool({...newSchool, active: e.target.value})}>
                <option value="true">Yes</option>
                <option value="false">No</option>
            </select>
            <button onClick={handleAddSchool}>Add School</button>
        </div>
    );
}

export default SchoolForm;
