DELETE FROM url_mapping;

DELETE FROM url_mapping_click;

-- Inserir dados na tabela url_mapping
INSERT INTO url_mapping (original_url, short_url) VALUES 
('https://www.example.com/page1', 'page1'),
('https://www.example.com/page2', 'page2'),
('https://www.example.com/page3', 'page3');

-- Inserir dados na tabela url_mapping_click
INSERT INTO url_mapping_click (url_click_time, url_mapping_id) VALUES 
('2024-06-09 10:00:00', 1),
('2024-06-09 10:15:00', 2),
('2024-06-09 10:30:00', 1);

