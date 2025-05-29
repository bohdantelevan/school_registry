import React,{ useState } from "react";
import SchoolTable from "./components/SchoolTable";
import SchoolForm from "./components/SchoolForm";
import 'react-confirm-alert/src/react-confirm-alert.css';

function App() {
    const [refreshTrigger, setRefreshTrigger] = useState(false);

    const handleRefresh = () => {
        setRefreshTrigger(!refreshTrigger);
    };

    return (
        <div>
            <SchoolForm onAdd={handleRefresh} />
            <SchoolTable refreshTrigger={refreshTrigger} />
        </div>
    );
}

export default App;
