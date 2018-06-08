from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from . import views

urlpatterns = [
    path('animais/', views.AnimalsList.as_view()),
    path('animais/<int:pk>/',views.AnimalDetail.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)