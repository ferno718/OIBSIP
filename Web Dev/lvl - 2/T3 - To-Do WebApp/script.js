let tasks = [];

function addTask() {
    const taskInput = document.getElementById('task-input');
    const taskText = taskInput.value.trim();
    if (taskText === '') {
        alert('Please enter a task.');
        return;
    }

    const task = {
        id: Date.now(),
        text: taskText,
        createdAt: new Date(),
        completed: false,
        completedAt: null,
        paused: false,
        pausedAt: null,
        resumedAt: null,
        totalTime: 0,
        timer: null // Timer for this task
    };

    tasks.push(task);
    renderTasks();
    taskInput.value = '';
}

function toggleComplete(id) {
    const taskIndex = tasks.findIndex(task => task.id == id);
    if (tasks[taskIndex].completed) {
        tasks[taskIndex].completed = false;
        tasks[taskIndex].completedAt = null;
    } else {
        tasks[taskIndex].completed = true;
        tasks[taskIndex].completedAt = new Date();
        
    }
    renderTasks();
}

function togglePause(id) {
    const taskIndex = tasks.findIndex(task => task.id == id);
    if (!tasks[taskIndex].paused) {
        tasks[taskIndex].paused = true;
        tasks[taskIndex].pausedAt = new Date();
        clearInterval(tasks[taskIndex].timer); // Stop the timer for this task
    } else {
        tasks[taskIndex].paused = false;
        tasks[taskIndex].resumedAt = new Date();
        const pausedTime = tasks[taskIndex].resumedAt - tasks[taskIndex].pausedAt;
        tasks[taskIndex].totalTime += pausedTime;
        tasks[taskIndex].timer = setInterval(() => {
            renderTasks();
        }, 1000); // Restart the timer for this task
    }
    renderTasks();
}

function deleteTask(id) {
    const taskIndex = tasks.findIndex(task => task.id == id);
    clearInterval(tasks[taskIndex].timer); // Stop the timer for this task
    tasks = tasks.filter(task => task.id != id);
    renderTasks();
}

function renderTasks() {
    const pendingTasksList = document.getElementById('pending-tasks');
    const completedTasksList = document.getElementById('completed-tasks');

    pendingTasksList.innerHTML = '';
    completedTasksList.innerHTML = '';

    tasks.forEach(task => {
        const li = document.createElement('li');
        let taskStatus = 'Paused';
        let startStopButton = `<button onclick="togglePause(${task.id})">${task.paused ? 'Stop' : 'Start'}</button>`;
        if (task.completed) {
            taskStatus = 'Completed';
            startStopButton = ''; // Remove the start/stop button for completed tasks
        } else if (task.paused) {
            taskStatus = 'Pending';
        }
        const taskTime = calculateTaskTime(task);
        li.innerHTML = `
            <span class="${task.completed ? 'completed' : ''}">${task.text}</span>
            <span>Status: ${taskStatus}</span>
            <span>Time: ${taskTime}</span>
            <div>
                <button onclick="toggleComplete(${task.id})">${task.completed ? 'Undo' : 'Complete'}</button>
                ${startStopButton}
                <button onclick="deleteTask(${task.id})">Delete</button>
            </div>
        `;

        if (task.completed) {
            completedTasksList.appendChild(li);
        } else {
            pendingTasksList.appendChild(li);
        }
    });
}

function calculateTaskTime(task) {
    let totalTime = task.totalTime;
    if (task.paused) {
        const pauseTime = new Date() - task.pausedAt;
        totalTime += pauseTime;
    }
    return formatTime(totalTime);
}

function formatTime(time) {
    const seconds = Math.floor((time / 1000) % 60);
    const minutes = Math.floor((time / (1000 * 60)) % 60);
    const hours = Math.floor((time / (1000 * 60 * 60)) % 24);
    return `${hours}h ${minutes}m ${seconds}s`;
}

renderTasks();