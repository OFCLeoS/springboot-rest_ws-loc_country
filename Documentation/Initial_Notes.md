# SpringBoot
- It seems that only a GET endpoint is required
- I will probably only need to use the "Controller" part of Springboot's MVC
- https://nominatim.org actually provides the exact needed functionality, via a GET request to: GET https://nominatim.openstreetmap.org/reverse?lat=[LATTITUDE]&lon=[LONGITUDE]&format=jsonv2
- Will do a "countrt getter" Spring Boot Service and a REST Controller with a Get Mapping