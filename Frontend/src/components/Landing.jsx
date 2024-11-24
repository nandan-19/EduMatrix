import React, { useState, useEffect } from 'react';
import './Landing.css';

const Landing = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setScrolled(window.scrollY > 50);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <div className="landing-page">
      {/* Navigation */}
      <nav className={`navbar ${scrolled ? 'scrolled' : ''}`}>
        <div className="logo">
          <div className="logo-icon">E</div>
          <span className="logo-text">EduMatrix</span>
        </div>
        <div className="nav-toggle" onClick={() => setIsMenuOpen(!isMenuOpen)}>
          <span></span>
          <span></span>
          <span></span>
        </div>
        <ul className={`nav-links ${isMenuOpen ? 'active' : ''}`}>
          <li><a href="#features">Features</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#contact">Contact</a></li>
          <li>
            <button className="login-btn">
              <span>Login</span>
            </button>
          </li>
        </ul>
      </nav>

      {/* Hero Section */}
      <section className="hero">
        <div className="hero-content">
          <div className="hero-text">
            <h1>
              <span className="gradient-text">Transform</span> Your 
              <span className="gradient-text"> Educational</span> Journey
            </h1>
            <p>Experience the future of education management with our innovative platform that brings together students, teachers, and technology.</p>
            <button className="cta-button">
              Get Started
              <span className="button-shine"></span>
            </button>
          </div>
          <div className="hero-stats">
            <div className="stat-card">
              <span className="stat-number">1000+</span>
              <span className="stat-label">Students</span>
            </div>
            <div className="stat-card">
              <span className="stat-number">100+</span>
              <span className="stat-label">Teachers</span>
            </div>
            <div className="stat-card">
              <span className="stat-number">50+</span>
              <span className="stat-label">Courses</span>
            </div>
          </div>
        </div>
        <div className="hero-shape"></div>
      </section>

      {/* Features Section */}
      <section id="features" className="features">
        <h2 className="section-title">Powerful Features</h2>
        <div className="feature-grid">
            <div className="feature-card">
                <div className="feature-icon attendance"></div>
                <h3>Smart Attendance</h3>
                <p>Track attendance effortlessly with our intelligent system powered by QR codes and facial recognition.</p>
            </div>
            <div className="feature-card">
                <div className="feature-icon materials"></div>
                <h3>Learning Hub</h3>
                <p>Access course materials, assignments, and resources in one centralized digital library.</p>
            </div>
            <div className="feature-card">
                <div className="feature-icon chatbot"></div>
                <h3>AI Assistant</h3>
                <p>Get instant help with our AI-powered chatbot that understands and answers your questions.</p>
            </div>
            <div className="feature-card">
                <div className="feature-icon analytics"></div>
                <h3>Smart Analytics</h3>
                <p>Gain insights with detailed performance analytics and progress tracking.</p>
            </div>
        </div>
      </section>

      {/* About Section */}
      <section id="about" className="about">
        <div className="about-content">
          <h2 className="section-title">Why Choose EduMatrix?</h2>
          <div className="benefits-grid">
            <div className="benefit-item">
              <div className="benefit-icon easy"></div>
              <h3>Easy to Use</h3>
              <p>Intuitive interface for both students and teachers</p>
            </div>
            <div className="benefit-item">
              <div className="benefit-icon secure"></div>
              <h3>Secure</h3>
              <p>Advanced security for your educational data</p>
            </div>
            <div className="benefit-item">
              <div className="benefit-icon support"></div>
              <h3>24/7 Support</h3>
              <p>Round-the-clock assistance when you need it</p>
            </div>
          </div>
        </div>
      </section>

      {/* Contact Section */}
      <section id="contact" className="contact">
        <div className="contact-container">
          <h2 className="section-title">Get in Touch</h2>
          <form className="contact-form">
            <div className="form-group">
              <input type="text" required placeholder='Name'/>
              
              <span className="focus-border"></span>
            </div>
            <div className="form-group">
              <input type="email" required placeholder='Email'/>
              <span className="focus-border"></span>
            </div>
            <div className="form-group">
              <textarea required placeholder='Message here...'></textarea>
              <span className="focus-border"></span>
            </div>
            <button type="submit" className="submit-btn">
              Send Message
            </button>
          </form>
        </div>
      </section>

      {/* Footer */}
      <footer className="footer">
        <div className="footer-content">
          <div className="footer-logo">
            <div className="logo-icon">E</div>
            <span>EduMatrix</span>
          </div>
          <p>Making education management smarter</p>
          <div className="social-links">
            <a href="#" className="social-icon facebook" title="Facebook"><i className="fab fa-facebook-f"></i></a>
            <a href="#" className="social-icon twitter" title="Twitter"><i className="fab fa-twitter"></i></a>
            <a href="#" className="social-icon instagram" title="Instagram"><i className="fab fa-instagram"></i></a>
          </div>
        </div>
        <div className="footer-bottom">
          <p>&copy; 2024 EduMatrix. All rights reserved.</p>
        </div>
      </footer>
    </div>
  );
};

export default Landing;