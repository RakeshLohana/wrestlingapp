// src/components/Upload.js

import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Upload() {
  const [video, setVideo] = useState(null);
  const [error, setError] = useState('');
  const history = useNavigate();

  const handleVideoChange = (e) => {
    setVideo(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!video) {
      setError('Please select a video to upload');
      return;
    }

    const formData = new FormData();
    formData.append('video', video);
    formData.append('userId', localStorage.getItem('userId'));

    try {
      const response = await axios.post('http://localhost:5000/api/videos/upload', formData, {
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`,
          'Content-Type': 'multipart/form-data',
        },
      });
      history.push('/videos'); // Redirect to videos page after upload
    } catch (err) {
      setError('Error uploading video');
    }
  };

  return (
    <div>
      <h2>Upload Video</h2>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit}>
        <input type="file" onChange={handleVideoChange} accept="video/*" required />
        <button type="submit">Upload</button>
      </form>
    </div>
  );
}

export default Upload;
