// src/components/Videos.js

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './css/Video.css'; // Import the CSS file

function Videos() {
  const [videos, setVideos] = useState([]);
  const [error, setError] = useState('');
  const userId = localStorage.getItem('userId'); // Get userId from localStorage

  useEffect(() => {
    const fetchVideos = async () => {
      if (!userId) {
        setError('User not logged in');
        return;
      }
      try {
        const response = await axios.get(`http://localhost:5000/api/videos/${userId}`, {
          headers: {
            'Authorization': `Bearer ${localStorage.getItem('token')}`,
          },
        });
        setVideos(response.data);
      } catch (err) {
        setError('Error fetching videos');
      }
    };

    fetchVideos();
  }, [userId]); // Re-run effect when userId changes

  return (
    <div className="container">
      <h2>Wrestling app</h2>
      {error && <p className="error">{error}</p>}
      <ul>
        {videos.map((video) => (
          <li key={video._id}>
            <video controls>
              <source src={`http://localhost:5000/uploads/${video.filename}`} type="video/mp4" />
              Your browser does not support the video tag.
            </video>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Videos;
