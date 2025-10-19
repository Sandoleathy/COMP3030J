# YiShan Villa - Smart Homestay Management System

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![RuoYi](https://img.shields.io/badge/RuoYi-v3.6.3-brightgreen.svg)](http://www.ruoyi.vip)

A comprehensive **Smart Homestay Management System** with real-time energy monitoring and weather data integration, built on enterprise-grade microservices architecture. This system provides full-stack hotel/homestay management capabilities including reservations, room management, financial tracking, and intelligent energy consumption analytics.

## 🏗️ System Architecture

### Microservices Architecture Overview

The system is built on **Spring Cloud & Alibaba** microservices architecture with the following core services:

```
┌─────────────────────────────────────────────────────────────┐
│                      Gateway (Port 8080)                     │
│                   Spring Cloud Gateway                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ├─────────────────────────────────┐
                              │                                 │
┌─────────────────────────────▼──────┐   ┌───────────────────▼─────┐
│   Authentication Service (9200)    │   │  System Service (9201)  │
│        OAuth2 + JWT                 │   │   User/Role/Menu Mgmt   │
└─────────────────────────────────────┘   └─────────────────────────┘
                              │
                              ├─────────────────────────────────┐
                              │                                 │
┌─────────────────────────────▼──────┐   ┌───────────────────▼─────┐
│    Homestay Service (Custom)       │   │ Statistics Service      │
│  - Reservation Management           │   │ - Energy Monitoring     │
│  - Room & Building Management       │   │ - Weather Integration   │
│  - Guest & Staff Management         │   │ - Real-time WebSocket   │
│  - Financial Operations             │   │ - Data Analytics        │
└─────────────────────────────────────┘   └─────────────────────────┘
                              │
                              ├─────────────────────────────────┐
                              │                                 │
┌─────────────────────────────▼──────┐   ┌───────────────────▼─────┐
│      File Service (9300)           │   │  Job Service (9203)     │
│    Distributed File Storage         │   │  Scheduled Tasks        │
└─────────────────────────────────────┘   └─────────────────────────┘
                              │
┌─────────────────────────────▼──────┐
│    Code Generator Service (9202)   │
│       MyBatis Generator             │
└─────────────────────────────────────┘
```

### Infrastructure Components

- **Service Registry & Discovery**: Alibaba Nacos
- **Configuration Center**: Nacos Config
- **API Gateway**: Spring Cloud Gateway
- **Circuit Breaker & Rate Limiting**: Alibaba Sentinel
- **Distributed Transaction**: Seata
- **Caching**: Redis
- **Message Queue**: WebSocket for real-time updates
- **Database**: MySQL 8.0
- **ORM Framework**: MyBatis Plus

## 🛠️ Technology Stack

### Backend Technologies

| Component | Technology | Version | Purpose |
|-----------|-----------|---------|---------|
| **Core Framework** | Spring Boot | 2.7.18 | Application framework |
| **Microservices** | Spring Cloud | 2021.0.8 | Service coordination |
| **Cloud Services** | Spring Cloud Alibaba | 2021.0.5.0 | Alibaba cloud integration |
| **Service Discovery** | Nacos | 2021.0.5.0 | Service registry |
| **API Gateway** | Spring Cloud Gateway | 3.1.x | API routing & filtering |
| **Security** | Spring Security + JWT | 5.7.x | Authentication & authorization |
| **Circuit Breaker** | Sentinel | 1.8.6 | Flow control & protection |
| **Database** | MySQL | 8.0+ | Relational data storage |
| **Connection Pool** | Druid | 1.2.20 | Database connection pooling |
| **ORM** | MyBatis Plus | 3.5.x | Object-relational mapping |
| **Cache** | Redis | Latest | Distributed caching |
| **Logging** | Logback + SLF4J | Latest | Application logging |
| **API Documentation** | Swagger 3 | 3.0.0 | API specification |
| **Build Tool** | Maven | 3.8+ | Dependency management |
| **Java Version** | JDK | 17 | Programming language |

### Frontend Technologies

| Component | Technology | Version | Purpose |
|-----------|-----------|---------|---------|
| **Framework** | Vue 3 | 3.4.21 | Progressive JavaScript framework |
| **Build Tool** | Vite | 5.1.6 | Next-generation frontend tooling |
| **Language** | TypeScript | 5.4.0 | Type-safe JavaScript |
| **UI Framework** | Element Plus | 2.6.3 | Vue 3 component library |
| **State Management** | Pinia | 2.1.7 | Vue store |
| **Routing** | Vue Router | 4.3.0 | Client-side routing |
| **HTTP Client** | Axios | 1.6.8 | Promise-based HTTP client |
| **Charts** | ECharts | 5.4.3 | Data visualization |
| **Charts** | Chart.js | 4.4.2 | Interactive charts |
| **3D Rendering** | Three.js | 0.163.0 | 3D graphics library |
| **Rich Text Editor** | Vue Quill | 1.2.0 | WYSIWYG editor |
| **Styling** | Sass | 1.69.5 | CSS preprocessor |
| **Internationalization** | Vue I18n | 10.0.0 | Multi-language support |
| **Icons** | Element Plus Icons | 2.3.1 | Icon components |
| **Encryption** | JSEncrypt | 3.3.2 | RSA encryption |
| **File Export** | FileSaver.js | 2.0.5 | Client-side file saving |

## 📦 Core Modules

### 1. Homestay Service Module

**Package**: `com.homestay`

The core business module for homestay/hotel operations management:

#### Controllers & Features

- **`HsReservationController`**: Reservation lifecycle management
  - Create, update, cancel reservations
  - Check-in/check-out processing
  - Reservation status tracking
  - Availability checking

- **`HsRoomController`**: Room inventory management
  - Room CRUD operations
  - Room status management (available, occupied, maintenance)
  - Room type classification
  - Pricing configuration

- **`HsBuildingTypeController`**: Property structure management
  - Building configuration
  - Floor plan management
  - Building type categorization

- **`HsBedTypeController`**: Bed configuration management
  - Bed type definitions
  - Capacity management
  - Amenity tracking

- **`HsGuestController`**: Guest information management
  - Guest registration
  - Profile management
  - History tracking

- **`HsStaffController`**: Staff management
  - Employee records
  - Role assignment
  - Schedule management

- **`HsFinanceController`**: Financial operations
  - Payment processing
  - Invoice generation
  - Revenue tracking
  - Expense management

- **`HsConsumptionController`**: Consumption tracking
  - Energy usage monitoring
  - Resource consumption logs
  - Cost calculation

- **`HsRoomImageController`** & **`HsBedImageController`**: Media management
  - Image upload and storage
  - Gallery management
  - Image association with rooms/beds

- **`HsRrController`**: Room-Reservation relationship management
  - Booking associations
  - Multi-room reservations
  - Relationship tracking

**Technical Features**:
- RESTful API design
- MyBatis Plus for database operations
- Transaction management with Spring
- Input validation with JSR-303
- Swagger API documentation
- Lombok for boilerplate reduction

### 2. Statistics Service Module

**Package**: `com.statistics`

Advanced analytics and monitoring capabilities:

#### Controllers & Features

- **`energyController`**: Energy consumption analytics
  - Real-time energy monitoring
  - Solar power generation tracking
  - Wind power statistics
  - Water consumption analysis
  - Energy efficiency metrics
  - Historical trend analysis
  - Cost calculation and optimization

- **`weatherController`**: Weather data integration
  - Real-time weather information
  - 7-day forecast integration
  - Temperature monitoring
  - Weather-based energy prediction
  - Alert system for extreme conditions

**Technical Features**:
- **WebSocket Support**: Real-time data push to frontend
- External API Integration for weather data
- Time-series data processing
- Data aggregation and analytics
- Scheduled data collection (Spring Scheduler)

### 3. System Service Module (RuoYi Core)

**Package**: `com.ruoyi.system`

Enterprise-grade system administration:

- User Management: User CRUD, authentication, profile management
- Department Management: Organizational structure, tree view
- Role Management: RBAC (Role-Based Access Control)
- Menu Management: Dynamic menu generation, permissions
- Dictionary Management: System data dictionary
- Parameter Management: System configuration
- Notice Management: Announcements and notifications
- Login/Operation Logs: Audit trail and security monitoring
- Online Users: Active session management

### 4. Gateway Service

**Package**: `com.ruoyi.gateway`

API Gateway with advanced routing and security:

- Dynamic routing configuration
- Load balancing
- Rate limiting and circuit breaking
- Request/response filtering
- Authentication pre-processing
- CORS handling
- Request logging and monitoring

### 5. Authentication Service

**Package**: `com.ruoyi.auth`

Centralized authentication and authorization:

- JWT token generation and validation
- OAuth2 protocol support
- Token refresh mechanism
- Single Sign-On (SSO) capability
- Password encryption (BCrypt)
- RSA key pair for secure transmission
- Captcha validation
- Multi-factor authentication support

### 6. File Service

**Package**: `com.ruoyi.file`

Distributed file storage and management:

- File upload/download
- Multiple storage backends support
- Image processing (resize, crop, compress)
- File access control
- CDN integration ready
- Thumbnail generation

### 7. Job Service

**Package**: `com.ruoyi.job`

Scheduled task management:

- Cron-based scheduling
- Dynamic job configuration
- Execution history and logs
- Failed task retry mechanism
- Concurrent task management

### 8. Code Generator Service

**Package**: `com.ruoyi.gen`

Rapid development tooling:

- Database table introspection
- Backend code generation (Controller, Service, Mapper)
- Frontend code generation (Vue components, API calls)
- CRUD template generation
- MyBatis XML generation
- Customizable templates

## 🎨 Frontend Architecture

### Component Structure

```
src/
├── components/
│   ├── Charts/                    # Data visualization components
│   │   ├── EnergyOverviewChart.vue
│   │   ├── TemperatureCard.vue
│   │   ├── WeatherForecastChart.vue
│   │   └── WindDetailChart.vue
│   ├── HotelManagement/          # Hotel operation components
│   │   ├── Building.vue
│   │   ├── HotelInfo.vue
│   │   ├── Reservation.vue
│   │   └── Room.vue
│   ├── ParkInformationBlock/     # Energy monitoring components
│   │   ├── EnergyOverviewCard.vue
│   │   ├── SolarDetail.vue
│   │   ├── WaterDetail.vue
│   │   └── WindDetail.vue
│   └── [Common Components]/       # Reusable UI components
├── views/                         # Page-level components
│   ├── AdminMenu.vue             # Admin dashboard
│   ├── DataOverView.vue          # Energy analytics
│   ├── ReservationView.vue       # Booking management
│   ├── Login.vue                 # Authentication
│   └── WeatherInfo.vue           # Weather monitoring
├── router/                        # Vue Router configuration
├── stores/                        # Pinia state management
├── utils/                         # Utility functions
└── api/                          # API service layer
```

### Key Frontend Features

1. **Responsive Design**: Mobile-first approach with breakpoint optimization
2. **Real-time Updates**: WebSocket integration for live data
3. **Data Visualization**: Interactive charts using ECharts and Chart.js
4. **3D Visualization**: Three.js integration for property models
5. **Rich Text Editing**: Quill editor for content management
6. **Form Validation**: Comprehensive validation rules
7. **Internationalization**: Multi-language support ready
8. **Theme Customization**: Dynamic theme switching
9. **Lazy Loading**: Component and route lazy loading for performance
10. **Progressive Web App (PWA)**: Offline capability ready

### State Management with Pinia

```typescript
// Example store structure
stores/
├── user.ts           // User authentication state
├── hotel.ts          // Hotel data state
├── reservation.ts    // Booking state
├── energy.ts         // Energy monitoring state
└── weather.ts        // Weather data state
```

## 🔐 Security Features

### Authentication & Authorization

1. **JWT Token-Based Authentication**
   - Access token with configurable expiration
   - Refresh token mechanism
   - Token blacklist for logout

2. **RBAC Permission System**
   - Role-based access control
   - Fine-grained permission management
   - Dynamic menu generation based on roles

3. **Security Measures**
   - Password encryption with BCrypt (strength 10)
   - RSA encryption for sensitive data transmission
   - XSS protection
   - CSRF token validation
   - SQL injection prevention (MyBatis parameterized queries)
   - Rate limiting to prevent DDoS
   - IP whitelist/blacklist support

4. **Data Security**
   - Sensitive field encryption in database
   - Audit logging for all operations
   - Data masking for PII (Personal Identifiable Information)

## 📊 Database Design

### Core Database Entities

#### Homestay Module Tables

- **`hs_reservation`**: Reservation records
- **`hs_room`**: Room inventory
- **`hs_guest`**: Guest information
- **`hs_staff`**: Staff records
- **`hs_building_type`**: Building configurations
- **`hs_bed_type`**: Bed type definitions
- **`hs_finance`**: Financial transactions
- **`hs_consumption`**: Energy consumption logs
- **`hs_room_image`**: Room media files
- **`hs_bed_image`**: Bed media files
- **`hs_rr`**: Room-Reservation relationships

#### Statistics Module Tables

- **`energy_data`**: Energy consumption records
- **`weather_data`**: Weather information logs
- **`solar_generation`**: Solar power data
- **`wind_generation`**: Wind power statistics
- **`water_consumption`**: Water usage logs

#### System Tables (RuoYi Framework)

- **`sys_user`**: System users
- **`sys_role`**: Roles
- **`sys_menu`**: Menu permissions
- **`sys_dept`**: Departments
- **`sys_dict_type`**: Dictionary types
- **`sys_dict_data`**: Dictionary data
- **`sys_config`**: System configuration
- **`sys_logininfor`**: Login logs
- **`sys_oper_log`**: Operation logs

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: 17 or higher
- **Maven**: 3.8 or higher
- **Node.js**: 18 or higher
- **npm/pnpm**: Latest version
- **MySQL**: 8.0 or higher
- **Redis**: 6.0 or higher
- **Nacos**: 2.x (for service discovery and configuration)

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Sandoleathy/COMP3030J.git
   cd COMP3030J/RuoYi-Cloud
   ```

2. **Configure MySQL Database**
   - Create a new database
   - Import the SQL schema files (if provided)
   - Update database configuration in Nacos or `application.yml`

3. **Configure Redis**
   - Ensure Redis is running on default port 6379
   - Update Redis configuration if using custom settings

4. **Configure Nacos**
   - Start Nacos server
   - Import configuration files
   - Update `bootstrap.yml` with Nacos server address

5. **Build the project**
   ```bash
   mvn clean install
   ```

6. **Start services in order**
   ```bash
   # 1. Start gateway
   cd ruoyi-gateway
   mvn spring-boot:run
   
   # 2. Start auth service
   cd ../ruoyi-auth
   mvn spring-boot:run
   
   # 3. Start system service
   cd ../ruoyi-modules/ruoyi-system
   mvn spring-boot:run
   
   # 4. Start homestay service
   cd ../homestay
   mvn spring-boot:run
   
   # 5. Start statistics service
   cd ../statistics
   mvn spring-boot:run
   ```

### Frontend Setup

1. **Navigate to frontend directory**
   ```bash
   cd COMP3030J/comp3030j-front
   ```

2. **Install dependencies**
   ```bash
   npm install
   # or
   pnpm install
   ```

3. **Configure API endpoint**
   - Update the proxy configuration in `vite.config.ts` if needed
   - Default gateway URL: `http://localhost:8080`

4. **Start development server**
   ```bash
   npm run dev
   ```

5. **Build for production**
   ```bash
   npm run build
   ```

### Access the Application

- **Frontend**: http://localhost:5173
- **Backend Gateway**: http://localhost:8080
- **API Documentation**: http://localhost:8080/swagger-ui.html

### Default Credentials

- **Admin Username**: admin
- **Admin Password**: admin123

⚠️ **Important**: Change default passwords immediately in production environment!

## 🔧 Configuration

### Backend Configuration

Key configuration files located in each service:

```yaml
# bootstrap.yml - Nacos configuration
spring:
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
      config:
        server-addr: localhost:8848
        file-extension: yml

# Application-specific configurations in Nacos Config Center
# - datasource configuration
# - redis configuration
# - jwt secret key
# - file storage path
```

### Frontend Configuration

```typescript
// vite.config.ts - Development proxy
server: {
  port: 5173,
  proxy: {
    '/api': {
      target: 'http://localhost:8080',
      changeOrigin: true,
      rewrite: path => path.replace(/^\/api/, '')
    }
  }
}
```

## 📈 Performance Optimization

### Backend Optimizations

1. **Database Optimization**
   - Index optimization on frequently queried fields
   - Connection pooling with Druid
   - Query result caching with Redis
   - Pagination for large datasets

2. **Microservices Optimization**
   - Service-level caching
   - Circuit breaker patterns with Sentinel
   - Asynchronous processing for heavy operations
   - Load balancing across service instances

3. **API Optimization**
   - Response compression (Gzip)
   - API result caching
   - Lazy loading for associated data

### Frontend Optimizations

1. **Build Optimization**
   - Code splitting and lazy loading
   - Tree shaking for unused code
   - Asset compression
   - CDN for static resources

2. **Runtime Optimization**
   - Virtual scrolling for large lists
   - Image lazy loading
   - Component-level caching
   - Debounce/throttle for frequent events

3. **Network Optimization**
   - Request batching
   - Response caching
   - WebSocket for real-time data (avoiding polling)

## 🧪 Testing

### Backend Testing

```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Generate test coverage report
mvn jacoco:report
```

### Frontend Testing

```bash
# Run unit tests
npm run test:unit

# Run e2e tests
npm run test:e2e

# Generate coverage report
npm run test:coverage
```

## 📦 Deployment

### Docker Deployment (Recommended)

```bash
# Build Docker images
docker-compose build

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

### Traditional Deployment

1. **Package backend services**
   ```bash
   mvn clean package -DskipTests
   ```

2. **Deploy JAR files to server**
   ```bash
   java -jar ruoyi-gateway.jar
   java -jar ruoyi-auth.jar
   java -jar ruoyi-system.jar
   java -jar homestay.jar
   java -jar statistics.jar
   ```

3. **Build and deploy frontend**
   ```bash
   npm run build
   # Deploy dist/ folder to Nginx/Apache
   ```

### Nginx Configuration Example

```nginx
server {
    listen 80;
    server_name your-domain.com;
    
    # Frontend static files
    location / {
        root /var/www/homestay-frontend;
        try_files $uri $uri/ /index.html;
    }
    
    # Backend API proxy
    location /api {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
    
    # WebSocket proxy
    location /ws {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
    }
}
```

## 🐛 Troubleshooting

### Common Issues

1. **Nacos Connection Failed**
   - Ensure Nacos server is running
   - Check network connectivity
   - Verify Nacos configuration in `bootstrap.yml`

2. **Database Connection Error**
   - Verify MySQL is running
   - Check database credentials
   - Ensure database exists and schema is imported

3. **Redis Connection Failed**
   - Verify Redis server is running
   - Check Redis configuration (host, port, password)

4. **Frontend API Errors**
   - Check if backend services are running
   - Verify proxy configuration in `vite.config.ts`
   - Check browser console for CORS errors

5. **Service Registration Failed**
   - Ensure Nacos is accessible
   - Check service name configuration
   - Verify network policies/firewall rules

## 📚 API Documentation

API documentation is available through Swagger UI once the services are running:

- **Gateway Swagger**: http://localhost:8080/swagger-ui.html
- **Homestay Service**: http://localhost:8080/homestay/swagger-ui.html
- **Statistics Service**: http://localhost:8080/statistics/swagger-ui.html
- **System Service**: http://localhost:8080/system/swagger-ui.html

### API Versioning

All APIs follow RESTful conventions with versioning:
- **v1**: `/api/v1/*` (current stable version)

### Authentication Required

Most APIs require JWT authentication:
```http
Authorization: Bearer <your-jwt-token>
```

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Code Style

- **Backend**: Follow Alibaba Java Coding Guidelines
- **Frontend**: Follow Vue 3 Style Guide and ESLint configuration
- Write unit tests for new features
- Update documentation for API changes

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Copyright (c) 2024 Sandoleathy

## 🙏 Acknowledgments

- **RuoYi Framework**: This project is built on top of the excellent [RuoYi-Cloud](https://gitee.com/y_project/RuoYi-Cloud) framework
- **Spring Cloud Alibaba**: For providing robust microservices infrastructure
- **Vue.js Community**: For the amazing frontend ecosystem
- **Element Plus**: For the comprehensive UI component library

## 📞 Support

For issues, questions, or suggestions:

- **GitHub Issues**: [Create an issue](https://github.com/Sandoleathy/COMP3030J/issues)
- **Email**: Contact the development team

## 🗺️ Roadmap

### Planned Features

- [ ] Mobile application (React Native/Flutter)
- [ ] Advanced analytics dashboard with AI predictions
- [ ] Integration with third-party booking platforms
- [ ] IoT device integration for smart room control
- [ ] Blockchain-based payment system
- [ ] Multi-tenant support for property management companies
- [ ] Advanced reporting and business intelligence
- [ ] Customer relationship management (CRM) module
- [ ] Loyalty program management
- [ ] Automated pricing optimization based on demand

---

**Built with ❤️ for COMP3030J Project**