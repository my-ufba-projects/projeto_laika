from django.db import models

# Classe mãe
class Estabelecimento(models.Model):
    nome = models.CharField(max_length=30)
    tipo_escolhas = ('1','Abrigo'), ('2','Clínica Veterinária')
    endereco = models.CharField(max_length=60)
    horFuncionamento = models.CharField(max_length=80)
    telefone = models.CharField(max_length=30)
    email = models.CharField(max_length=30)
    obs = models.CharField(max_length=100)

    def save(self, *args, **kwargs):
        super(Estabelecimento, self.save(*args, **kwargs))


#Classe destinada ao cadastro de Abrigos
class Abrigo(Estabelecimento):
    atividades = models.CharField(max_length=250)

    def save(self, *args, **kwargs):
        super(Abrigo, self.save(*args, **kwargs))

    class Meta:
        verbose_name_plural = 'Abrigos'

# Classe destinada ao cadastro de Clínicas Veterinárias
class ClinicaVeterinaria(Estabelecimento):
    veterinarioResp = models.CharField(max_length=80)
    crmvVetResp = models.CharField(max_length=15)
    servicosOferecidos = models.CharField(max_length=250)

    def save(self, *args, **kwargs):
        super(ClinicaVeterinaria, self.save(*args, **kwargs))

    class Meta:
        verbose_name_plural = 'Clínicas'