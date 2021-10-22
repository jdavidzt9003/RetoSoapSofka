Feature: Busque un nombre de idioma basado en el código de idioma ISO
  Como automatizador de pruebas de un servicio SOAP
  necesito validar que el servicio de Lenguage Name devuelva el nombre del lenguaje
  para asegurar que pertenece al codigo ISO buscado

  Scenario: Buscar el nombre de idioma con el código iso
    Given que el automatizador ingresa un código iso "afr"
    When el automatizador ejecuta el servicio
    Then el servicio Language Name arroja el nombre del idioma de acuerdo al código iso "Afrikaans"

  Scenario: Validar mensaje de código ISO no encontrado
    Given el automatizador ingresa el "af" del idioma
    When el automatizador ejecuta el servicio para obtener el nombre del idioma
    Then el servicio Language Name arroja el mensaje de código no encontrado "Language ISO Code not found!"
