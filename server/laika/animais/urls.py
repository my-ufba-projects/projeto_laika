from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from . import views

urlpatterns = [
    path('animais/', views.AnimaisLista.as_view()),
    path('animais/<int:pk>/',views.AnimalDetalhe.as_view()),
    path('animais/caes/', views.CaesLista.as_view()),
    path('animais/caes/<int:pk>/', views.CaoDetalhe.as_view()),
    path('usuarioanimais/', views.UserAnimaisLista.as_view()),
    path('usuarioanimais/<int:pk>/', views.UserAnimalDetalhe.as_view()),
    path('usuarioanimais/usuariocaes/', views.UserCaesLista.as_view()),
    path('usuarioanimais/usuariocaes/<int:pk>', views.UserCaoDetalhe.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)