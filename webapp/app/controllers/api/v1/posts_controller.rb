module Api
	module V1
		class PostsController < ApplicationController
			def index
				@posts = Post.all # limit by area and sort by time (easy, just use geocoder for location and .order('created_at desc'))
				# may have to paginate
				render json: @posts
			end
			def recent
				@posts = Post.all.order('created_at desc').limit(5)
				render json: @posts
			end
			def closest
				# use geocoder later
				@posts = Post.all.order('created_at desc').limit(5)
				render json: @posts
			end
			def popular
				# does a ratio of amount of likes to hours
				@posts = Post.all.includes(:likes).sort_by{|att| att.likes.to_a.size/((Time.zone.now-att.created_at)/3600)}.reverse.take(5)
				render json: @posts
			end
			def create
				@post = Post.new(post_params)
				if @post.save
					render json: @post
				else
					render json: @post.errors.full_messages
				end
			end
			def show
				begin
					@post = Post.find(params[:id])
					render json: @post
				rescue
					render json: {error: "Resource not found"}.to_json, status: 404
				end
			end
			def update
				@post = Post.find(params[:id])
				if @post.update_attributes(update_params)
					render json: @post
				else
					render json: @post.errors.full_messages
				end
			end
			def destroy
				@post = Post.find(params[:id])
				@post.destroy
				render json: @post
			end
			private
			def post_params
				params.require(:post).permit(:message,:live,:lat,:long,:severity,:rating) # not sure if the severity and rating is for the user to edit or for everyone to edit
			end
			def update_params
				params.require(:post).permit(:message,:live)
			end
		end
	end
end
