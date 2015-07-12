Rails.application.routes.draw do
  namespace :api do
    namespace :v1 do
    	get 'posts/closest'=>'posts#closest'
      get 'posts/popular'=>'posts#popular'
      get 'posts/recent'=>'posts#recent'
      resources :posts do
        resources :likes
      end
    end
  end
end
