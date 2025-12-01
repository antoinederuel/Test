import { useState, useEffect } from 'react';

// 1. Définition du type (Doit correspondre exactement à ton Java Task.java)
interface Task {
  id: number;
  title: string;
  completed: boolean;
}

function App() {
  // --- ÉTAT (STATE) ---
  const [tasks, setTasks] = useState<Task[]>([]);
  const [newTaskTitle, setNewTaskTitle] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  // URL de ton API Backend
  const API_URL = 'http://localhost:8080/tasks';

  // --- LOGIQUE (ACTIONS) ---

  // 1. Charger les tâches au démarrage
  useEffect(() => {
    fetchTasks();
  }, []);

  const fetchTasks = () => {
    setIsLoading(true);
    fetch(API_URL)
      .then(response => response.json())
      .then(data => {
        setTasks(data);
        setIsLoading(false);
      })
      .catch(err => {
        console.error("Erreur de chargement:", err);
        setIsLoading(false);
      });
  };

  // 2. Ajouter une tâche (Appelle le POST Java)
  const handleAddTask = () => {
    if (!newTaskTitle.trim()) return; // On n'envoie pas de vide

    const taskToSend = { title: newTaskTitle, completed: false };

    fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(taskToSend)
    })
      .then(response => response.json())
      .then((savedTask) => {
        // On ajoute la nouvelle tâche à la liste locale directement
        setTasks([...tasks, savedTask]); 
        setNewTaskTitle(''); // On vide l'input
      })
      .catch(err => console.error("Erreur d'ajout:", err));
  };

  // 3. Supprimer une tâche (Appelle le DELETE Java)
  const handleDeleteTask = (id: number) => {
    fetch(`${API_URL}/${id}`, {
      method: 'DELETE',
    })
      .then(() => {
        // On supprime la tâche de l'affichage local
        setTasks(tasks.filter(t => t.id !== id));
      })
      .catch(err => console.error("Erreur de suppression:", err));
  };

  // --- RENDU (VISUEL) ---
  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4 font-sans">
      <div className="bg-white rounded-xl shadow-xl p-8 w-full max-w-lg border border-gray-100">
        
        {/* TITRE */}
        <h1 className="text-3xl font-bold text-gray-800 mb-8 text-center">
          🚀 DevOps Todo
        </h1>

        {/* FORMULAIRE D'AJOUT */}
        <div className="flex gap-2 mb-8">
          <input
            type="text"
            value={newTaskTitle}
            onChange={(e) => setNewTaskTitle(e.target.value)}
            onKeyDown={(e) => e.key === 'Enter' && handleAddTask()}
            placeholder="Nouvelle tâche..."
            className="flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500 transition"
          />
          <button 
            onClick={handleAddTask}
            className="bg-blue-600 hover:bg-blue-700 text-white font-bold px-6 py-2 rounded-lg transition shadow-md"
          >
            Ajouter
          </button>
        </div>

        {/* LISTE DES TÂCHES */}
        <div className="space-y-3">
          {isLoading ? (
            <p className="text-center text-gray-500">Chargement...</p>
          ) : tasks.length === 0 ? (
            <p className="text-center text-gray-400 italic">Aucune tâche pour l'instant.</p>
          ) : (
            tasks.map((task) => (
              <div 
                key={task.id} 
                className="group flex items-center justify-between p-4 rounded-lg border border-gray-200 bg-white hover:shadow-md transition-all"
              >
                <div className="flex items-center gap-3">
                  <span className="text-xl">
                    {task.completed ? '✅' : '⬜'}
                  </span>
                  <span className={`font-medium text-gray-700 ${task.completed ? 'line-through text-gray-400' : ''}`}>
                    {task.title}
                  </span>
                </div>

                {/* Bouton Supprimer (n'apparaît qu'au survol grâce à group-hover) */}
                <button
                  onClick={() => handleDeleteTask(task.id)}
                  className="text-red-400 hover:text-red-600 hover:bg-red-50 p-2 rounded-full transition opacity-0 group-hover:opacity-100"
                  title="Supprimer"
                >
                  🗑️
                </button>
              </div>
            ))
          )}
        </div>

        <p className="mt-8 text-center text-xs text-gray-400">
          Connecté à Spring Boot sur le port 8080
        </p>

      </div>
    </div>
  );
}

export default App;


// Note: N'oublie pas de configurer les CORS dans ton backend Spring Boot pour permettre les requêtes depuis ton frontend React.