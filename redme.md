🚀 Microservices Payment & Order System

Este projeto demonstra uma arquitetura de microserviços baseada em eventos utilizando Spring Boot, Apache Kafka e PostgreSQL, simulando um fluxo real de criação de pedidos e processamento de pagamentos.

🧩 Arquitetura

O sistema é composto por dois microserviços independentes:

🟡 ms-order

Responsável por:

Criar pedidos
Persistir dados no PostgreSQL
Publicar eventos no Kafka (order-created)
Consumir resultado de pagamento (payment-success)
🟢 ms-payment

Responsável por:

Consumir eventos de pedidos
Processar pagamento
Publicar resultado no Kafka (payment-success)
🔄 Fluxo de eventos
Client → ms-order → Kafka (order-created) → ms-payment → Kafka (payment-success) → ms-order
🛠️ Tecnologias utilizadas
Java 17
Spring Boot
Spring Data JPA
Apache Kafka
PostgreSQL
Docker & Docker Compose
Kafka UI (monitoramento de mensagens)
⚙️ Como executar o projeto
1. Subir infraestrutura
docker-compose up -d

Isso inicia:

Kafka
Kafka UI (http://localhost:8080
)
PostgreSQL (Order DB e Payment DB)
2. Rodar os microserviços

Execute separadamente:

ms-order → port: 8082
ms-payment → port: 8081
📦 Endpoints principais
Criar pedido
POST /orders
Body
{
  "amount": 150.00
}
Health check
GET /health
🧠 Conceitos aplicados

Este projeto foi desenvolvido para praticar:

Arquitetura baseada em eventos (Event Driven Architecture)
Producer e Consumer no Kafka
Comunicação assíncrona entre serviços
Idempotência de processamento
Separação de responsabilidades
Persistência com JPA
Containerização com Docker
📊 Kafka Topics
Topic	Descrição
order-created	Evento de criação pedido
payment-success	Resultado do pagamento
🖥️ Kafka UI

Acesse para visualizar mensagens:

http://localhost:8080
📌 Estrutura do projeto
ms-order/
 ├── controller
 ├── service
 ├── entity
 ├── repository
 ├── kafka (producer/consumer)

ms-payment/
 ├── controller
 ├── service
 ├── entity
 ├── kafka (consumer/producer)
🚀 Próximos passos (melhorias futuras)
Implementação de Retry e Dead Letter Queue (DLQ)
Saga Pattern para consistência distribuída
Observabilidade com Prometheus + Grafana
Testes de integração com Testcontainers
Autenticação entre serviços
👨‍💻 Autor

Desenvolvido como prática de arquitetura de microserviços com foco em sistemas distribuídos e mensageria.

⭐ Se este projeto te ajudou

Deixe uma estrela no repositório para apoiar o conteúdo!